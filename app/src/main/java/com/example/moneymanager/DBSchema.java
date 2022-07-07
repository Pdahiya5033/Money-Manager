package com.example.moneymanager;

import android.provider.BaseColumns;

public class DBSchema {
    public static final class DataTable{
        public static final String NAME="ExpenseSavings";
        public static final class DataColumns implements BaseColumns{
            public static final String DATE="date";
            public static final String INCOME="income";
            public static final String CATEXPENSE="catExpense";
            public static final String CATEGORY="category";
            public static final String NOTE="note";
            public static final String INC_CAT="incCat";
            public static final String ACCOUNT="account";
        }

    }
}
