package com.lavu.internpro.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void delete(String storedFilename) throws IOException;

	Resource loadAsResource(String filename) throws FileNotFoundException;

	Path load(String filename);

	void store(MultipartFile file, String storedFilename);

	String getStoredFileName(MultipartFile file);

	void init();

}
