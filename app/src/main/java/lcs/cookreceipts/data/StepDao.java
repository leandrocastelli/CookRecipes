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
public interface StepDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addStep(Step step);

    @Query("select * from step where receipt_id = :receiptID")
    List<Step>getStepsFromReceipt(int receiptID);

    @Query("select * from step where receipt_id = :receiptID and step = :step")
    Step getStep(int receiptID, String step);
}
