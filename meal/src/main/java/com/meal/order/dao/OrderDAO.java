package com.meal.order.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.meal.cart.vo.CartVO;
import com.meal.order.vo.OrderVO;

public interface OrderDAO {
	public int insertOrder(OrderVO orderVO) throws DataAccessException;

	public List<OrderVO> selectUserOrders(String u_id) throws DataAccessException;
	
	public List<OrderVO> UserboardOrderPage(HashMap<String, Object> map) throws DataAccessException;

	public List<OrderVO> selectorderList(HashMap<String, String> OrderState) throws DataAccessException;

	public String findU_id(int o_id) throws DataAccessException;

	public void deleteOrder(int o_id) throws DataAccessException;

	public String overlappedO_id(int o_id) throws DataAccessException;

	public List<OrderVO> OrderResult(String parentNo) throws DataAccessException;

	public List<OrderVO> UserboardOrderListPage(HashMap<String, Object> pagingMap)throws DataAccessException;

	public List<OrderVO> tabpageorderlist(HashMap<String, Object> infoMap)throws DataAccessException;

	public String selectMaxParentNO(String u_id)throws DataAccessException;
	
	//판매자 주문내역 출력을 위해 사용 0614
	public List<OrderVO> orderSellerList(String s_id) throws DataAccessException;

	//6.14카트오더 추가
	public int MaxOrderNum() throws DataAccessException;
		
	public void insertCartOrder(OrderVO orderVO) throws DataAccessException;
}
