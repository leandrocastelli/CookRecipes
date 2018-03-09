package lcs.cookreceipts.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Leandro on 2/25/2018.
 */
@Dao
public interface ReceiptDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addReceipt(Receipt receipt);

    @Query("Select * from  receipt")
    List<Receipt> getAllReceipts();

    @Query("Select * from  receipt where id = :receiptId")
    Receipt getReceiptFromId(int receiptId);

    @Query("delete from receipt")
    void deleteAllReceipts();

}
