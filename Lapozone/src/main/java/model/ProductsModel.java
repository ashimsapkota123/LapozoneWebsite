package model;

import java.io.Serializable;

public class ProductsModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String productID;
	private String productName;
	private String screenSize;
	private String processor;
	private String ram;
	private String features;
	private Double discount;
	private String storage;
	private String warranty;
	private Double price;
	private Double discountAmount;
	private String imageUrl;

	private String quantity;

	public ProductsModel() {

	}

	public ProductsModel(String productID, String productName, String screenSize, String processor, String ram,
			String features, Double discount, String storage, String warranty, Double price,
			Double discountAmount, String imageUrl) {
		this.productID = productID;
		this.productName = productName;
		this.screenSize = screenSize;
		this.processor = processor;
		this.ram = ram;
		this.features = features;
		this.discount = discount;
		this.storage = storage;
		this.warranty = warranty;
		this.price = price;
		this.discountAmount = discountAmount;
		this.imageUrl = imageUrl;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getquantity() {
		return quantity;
	}

	public void setquantity(String quantity) {
		this.quantity = quantity;
	}

}
