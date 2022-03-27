package com.lavu.internpro.enums;

public enum ProductStatus {

	ACTICE("Active"), HIDE("Hide");

	private final String name;

	public String getName() {
		return name;
	}
	ProductStatus(String name) {
		this.name = name;
	}
//	@JsonCreator
//	public static ProductStatus getProductStatusFromCode(String value) {
//		for(ProductStatus pro : ProductStatus.values()) {
//			if(pro.getName().equals(value)) {
//				return pro;
//			}
//		}
//		return null;
//	}
}
