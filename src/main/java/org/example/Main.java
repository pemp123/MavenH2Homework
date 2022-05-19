package org.example;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<TableList> tableLists = new ArrayList<>();
        ArrayList<TableCols> tableCols = new ArrayList<>();
        ArrayList<TableCols> resultList = new ArrayList<>();
        String sqlQuery1 = "SELECT * FROM TABLE_LIST";
        String sqlQuery2 = "SELECT * FROM TABLE_COLS";
        Statement stmt1,stmt2;
        ResultSet rs1,rs2;
        String tmp1,tmp2;
        String[] tmpArray;
        try {
            Connection conn = DriverManager.
                    getConnection("jdbc:h2:./db/db", "pemp", "123");
            System.out.println("Connected to DB");
            stmt1 = conn.createStatement();
            rs1 = stmt1.executeQuery(sqlQuery1);
            while (rs1.next()){
                tmp1 =rs1.getString(1);
                tmp2 = rs1.getString(2);
                if (tmp2.contains(","))
                {
                    tmpArray = tmp2.split(", ");
                    for (int i = 0; i< tmpArray.length; i++)
                        tableLists.add(new TableList(tmp1,tmpArray[i]));
                }
                else
                    tableLists.add(new TableList(rs1.getString(1),rs1.getString(2)));
            }
//            for(int i = 0; i< tableLists.size(); i++) {
//               tableLists.get(i).printString();
//            }
//            System.out.println("---------------------------");
            stmt2 = conn.createStatement();
            rs2 = stmt2.executeQuery(sqlQuery2);
            while (rs2.next()){
                tableCols.add(new TableCols(rs2.getString(1),rs2.getString(2),rs2.getString(3)));
            }
//            for(int i = 0; i< tableCols.size(); i++) {
//                tableCols.get(i).printString();
//            }
//            System.out.println("---------------------------");
            for(int i = 0; i< tableLists.size(); i++) {
                for(int j = 0; j< tableCols.size(); j++) {
                    if  (tableLists.get(i).getTable_name().equalsIgnoreCase(tableCols.get(j).getTable_name())
                            && tableLists.get(i).getPk().equalsIgnoreCase((tableCols.get(j).getColumn_name())))
                    {
                        resultList.add(new TableCols(tableLists.get(i).getTable_name(),tableLists.get(i).getPk(),
                                tableCols.get(j).getColumn_type()));
                    }
                }

            }
//            for(int i = 0; i< resultList.size(); i++) {
//                resultList.get(i).printString();
//            }
            try(FileWriter writer = new FileWriter("result.txt", false))
            {
                for (int i = 0; i < resultList.size(); i++)
                {
                    writer.write(resultList.get(i).getTable_name()+", "+resultList.get(i).getColumn_name()
                            +", "+resultList.get(i).getColumn_type());
                    writer.append('\n');
                }
                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
            conn.close();
            System.out.println("Disconnected from DB");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}