package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao {
	
	//To get the menu items list for Admin
	public List<MenuItem> getMenuItemListAdmin(){
		List<MenuItem> menuItemListAdmin=new ArrayList<MenuItem>();
		try (Connection cn=ConnectionHandler.getConnection();){
			String sql="select * from menu_item m";
			PreparedStatement st=cn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				MenuItem mi=new MenuItem(rs.getLong("menu_item_id"), rs.getString("name"), rs.getFloat("price"), 
						rs.getBoolean("active"), rs.getDate("date_of_launch"), rs.getString("category"), rs.getBoolean("free_delivery"));
				menuItemListAdmin.add(mi);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItemListAdmin;
	}
	
	//To get the menu items list for Customer
	public List<MenuItem> getMenuItemListCustomer(){
		List<MenuItem> menuItemListCustomer=new ArrayList<MenuItem>();
		try (Connection cn=ConnectionHandler.getConnection();){
			String sql="select menu_item_id,name,price,active,date_of_launch,category,free_delivery from menu_item where date_of_launch<=CURDATE() and active='1'";
			PreparedStatement st=cn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				MenuItem mi1=new MenuItem(rs.getLong("menu_item_id"), rs.getString("name"), rs.getFloat("price"), 
						rs.getBoolean("active"), rs.getDate("date_of_launch"), rs.getString("category"), rs.getBoolean("free_delivery"));
				menuItemListCustomer.add(mi1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItemListCustomer;
	}
	
	//To get the menu item from menu_item table based on id
	public MenuItem getMenuItem(long menuItemId) {
		MenuItem mi=null;
		try (Connection cn=ConnectionHandler.getConnection();){
			String sql="SELECT * FROM menu_item where menu_item_id="+menuItemId;
			PreparedStatement st=cn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				mi=new MenuItem(rs.getLong("menu_item_id"), rs.getString("name"), rs.getFloat("price"), 
						rs.getBoolean("active"), rs.getDate("date_of_launch"), rs.getString("category"), rs.getBoolean("free_delivery"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mi;
	}
	
	//To edit or update the details of menu item
	public void editMenuItem(MenuItem menuItem) {
	
		long menuItemId=menuItem.getId();
		try (Connection cn=ConnectionHandler.getConnection();){
			String sql="update menu_item set name=?,price=?,active=?,date_of_launch=CURDATE(),category=?,free_delivery=? where menu_item_id="
					+menuItemId;
			PreparedStatement st=cn.prepareStatement(sql);
			st.setString(1, menuItem.getName());
			st.setFloat(2, menuItem.getPrice());
			st.setBoolean(3, menuItem.isActive());
			st.setString(4, menuItem.getCategory());
			st.setBoolean(5, menuItem.isFreeDelivery());
			int rs=st.executeUpdate();
			if(rs==1) {
				System.out.println("Item Modified SUCCESSFULLY!!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	@Override
	public void modifyMenuItem(MenuItem menuItem) {}
}
