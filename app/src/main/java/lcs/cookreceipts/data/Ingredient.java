package lcs.cookreceipts.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

/**
 * Created by Leandro on 2/24/2018.
 */
@Entity(tableName = "ingredient",
        foreignKeys = @ForeignKey(entity = Receipt.class,
                                    parentColumns = "id",
                                    childColumns = "receipt_id"),
        primaryKeys = {"receipt_id", "ingredient"})
public class Ingredient {


    public int getReceipt_id() {
        return receipt_id;
    }

    public void setReceipt_id(int receipt_id) {
        this.receipt_id = receipt_id;
    }

    public int receipt_id;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String quantity;
    public String measure;
    @NonNull
    public String ingredient;

    public Ingredient(int receipt_id, String quantity, String measure, String ingredient) {
        this.receipt_id = receipt_id;
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(ingredient)
                .append('\n')
                .append(quantity)
                .append('\n')
                .append(measure)
                .append('\n').toString();
    }
}
