package co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Romero-Pc on 11/06/2017.
 */

public class ItemResults {
    public static final String TITLE = "title";
    public static final String PRICE = "price";
    public static final String AVAILABLE = "available_quantity";
    public static final String PERMALINK = "permalink";
    public static final String THUMBNAIL = "thumbnail";
    public static final String ADDRESS = "address";
    public static final String ID = "id";

    @DatabaseField(columnName = ID, generatedId = true)
    private int id;
    @DatabaseField(columnName = TITLE)
    @SerializedName("title")
    private String title;
    @DatabaseField(columnName = PRICE)
    @SerializedName("price")
    private String price;
    @DatabaseField(columnName = AVAILABLE)
    @SerializedName("available_quantity")
    private int availableQuantity;
    @DatabaseField(columnName = PERMALINK)
    @SerializedName("permalink")
    private String permalink;
    @DatabaseField(columnName = THUMBNAIL)
    @SerializedName("thumbnail")
    private String thumbnail;
    @DatabaseField(columnName = ADDRESS, foreign = true)
    @SerializedName("address")
    private Address address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
