package lcs.cookreceipts.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Leandro on 2/25/2018.
 */
@Database(entities = {Ingredient.class, Receipt.class, Step.class}, version = 3, exportSchema = false)
public abstract class ReceiptDatabase extends RoomDatabase {
    private static ReceiptDatabase mInstance;

    public abstract IngredientDao ingredientDao();
    public abstract ReceiptDao receiptDao();
    public abstract StepDao stepDao();

    public static ReceiptDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, ReceiptDatabase.class, "receiptDB")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return mInstance;
    }
}
