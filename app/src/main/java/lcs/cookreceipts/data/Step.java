package lcs.cookreceipts.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Leandro on 2/24/2018.
 */
@Entity(tableName = "step",
        foreignKeys = @ForeignKey(entity = Receipt.class,
        parentColumns = "id",
        childColumns = "receipt_id"),
        primaryKeys = {"receipt_id", "step"})
public class Step {

    public int getReceipt_id() {
        return receipt_id;
    }

    public void setReceipt_id(int receipt_id) {
        this.receipt_id = receipt_id;
    }

    public int receipt_id;

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @NonNull
    public String step;
    public String shortDescription;
    public String description;
    public String videoURL;



    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String thumbnailURL;

    public Step(int receipt_id, String step, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.receipt_id = receipt_id;
        this.step = step;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }
}
