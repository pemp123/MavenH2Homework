package org.example;

public class TableCols {
    private String table_name;
    private String column_name;
    private String column_type;

    public TableCols(String t_name, String c_name, String c_type)
    {
        this.table_name = t_name;
        this.column_name = c_name;
        this.column_type = c_type;
    }
    public String getTable_name() {
        return table_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public String getColumn_type() {
        return column_type;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public void setColumn_type(String column_type) {
        this.column_type = column_type;
    }

    public void printString()
    {
        System.out.println(this.table_name + " " + this.column_name + " " + this.column_type);
    }
}
