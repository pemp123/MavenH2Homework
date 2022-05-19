package org.example;

public class TableList {
    private String table_name;
    private String pk;

    public TableList(String t_name, String pk)
    {
        this.table_name = t_name;
        this.pk = pk;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public void printString()
    {
        System.out.println(this.table_name+" "+ this.pk);
    }
}
