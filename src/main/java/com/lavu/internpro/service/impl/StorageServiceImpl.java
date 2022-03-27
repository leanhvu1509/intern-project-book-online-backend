package com.lavu.internpro.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lavu.internpro.exception.FileStorageException;
import com.lavu.internpro.service.StorageService;
import com.lavu.internpro.utils.StorageProperties;

@Service
public class StorageServiceImpl implements StorageService{

	private final Path rootLocation;

	public StorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
			System.out.println(rootLocation.toString());
		} catch (Exception e) {
			throw new FileStorageException("Could not initialize store");
		}
	}

	@Override
	public String getStoredFileName(MultipartFile file) {
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		String genaratedFilename = UUID.randomUUID().toString().replace("-", "");
		genaratedFilename = genaratedFilename + "." + fileExtension;
		return genaratedFilename;
	}

	private boolean isImageFile(MultipartFile file) {
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		return Arrays.asList(new String[] { "png", "jpg", "jpeg", "bmp", "svg" })
				.contains(fileExtension.trim().toLowerCase());
	}

	@Override
	public void store(MultipartFile file, String storedFilename) {
		try {
			if (file.isEmpty()) {
				throw new FileStorageException("Failed to store emptyy file!");
			}
			// check file is image?
			if (!isImageFile(file)) {
				throw new FileStorageException("You can only upload image file.");
			}
			// file must be <5mb
			float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
			if (fileSizeInMegabytes > 5.0f) {
				throw new FileStorageException("File must be <= 5 Mb");
			}

			Path destinationFile = this.rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new FileStorageException("Cannot store file outside current directory!");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) throws FileNotFoundException  {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			throw new FileNotFoundException("Could not read file " + filename);
		} catch (Exception e) {
			throw new FileNotFoundException("Could not read file " + filename);
		}
	}

	@Override
	public void delete(String storedFilename) {
		
		Path destinationFile = rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
		try {
			Files.delete(destinationFile);
		} catch (IOException e) {
			throw new FileStorageException("Failed!!");
		}
	}
}
