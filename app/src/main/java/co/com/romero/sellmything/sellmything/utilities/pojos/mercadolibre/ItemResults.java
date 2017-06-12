package co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Romero-Pc on 11/06/2017.
 */

public class ItemResults {
    @SerializedName("title")
    private String title;
    @SerializedName("seller")
    private Seller seller;
    @SerializedName("price")
    private String price;
    @SerializedName("available_quantity")
    private int availableQuantity;
    @SerializedName("permalink")
    private String permalink;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("address")
    private Address address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
