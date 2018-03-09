package lcs.cookreceipts.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Leandro on 2/25/2018.
 */
@Dao
public interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(Ingredient ingredient);

    @Query("select * from ingredient where receipt_id = :receiptId")
    List<Ingredient> getIngredientfromReceipt(int receiptId);
}
