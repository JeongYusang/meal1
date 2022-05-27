package com.meal.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meal.common.controller.BaseController;
import com.meal.goods.dao.GoodsDAO;
import com.meal.goods.service.GoodsService;
import com.meal.goods.vo.GoodsVO;
import com.meal.member.vo.MemberVO;
import com.meal.order.dao.OrderDAO;
import com.meal.order.service.OrderService;
import com.meal.order.vo.OrderVO;


@RequestMapping("/order")
@Controller("orderController")
public class OrderControllerImpl extends BaseController implements OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderControllerImpl.class);
		
		@Autowired
		OrderService orderService;
		
		@Autowired
		OrderDAO orderdao;
		
		@Autowired
		GoodsService goodsService;
		
		@Autowired
		GoodsDAO goodsdao;
		
		
		
		@Override
		@RequestMapping(value ="/insertOrder.do", method = RequestMethod.POST)
		public ModelAndView insertOrder(@ModelAttribute("orderVO") OrderVO _orderVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			HttpSession session = request.getSession();
			try {
			orderService.insertOrder(_orderVO);
			String viewName = (String) session.getAttribute("viewName");
			String message = "주문완료 되었습니다.";
			mav.addObject(message);
			mav.setViewName(viewName);
			return mav;
		}
			catch(Exception e){
				String viewName1 = "redirect:/order/orderform.do";
				mav.setViewName(viewName1);
				String message = "주문중 오류가 발생 하였습니다. 다시 주문해주시길 바랍니다";
				mav.addObject(message);
				return mav;
			}
		}
		
		@Override
		@RequestMapping(value ="/selectUserOrders.do", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView selectUserOrders(@RequestParam(value = "dateMap", required = false) Map<String, Object> dateMap,
				@RequestParam(value = "section", required = false) String section,
				@RequestParam(value = "pageNum", required = false) String pageNum,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			String viewName = (String) request.getAttribute("viewName");
			System.out.println("뷰네임" + viewName);
			ModelAndView mav = new ModelAndView(viewName);
			MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo");
			if(memberInfo != null) {
				String u_id = memberInfo.getU_id();
				HashMap<String, Object> Map = new HashMap<String, Object>();
				Map.put("pageNum", pageNum);
				Map.put("section", section);
				HashMap<String, Object> pagingMap = (HashMap<String, Object>) paging(Map);
				pagingMap.put("u_id", u_id);
				List<OrderVO> OrderVO = orderService.UserboardOrderPage(pagingMap);
				List<OrderVO> OrderList = orderService.selectUserOrders(u_id);
				Map<String,List<OrderVO>> orderMap = orderService.orderlist(u_id);
				mav.addObject("orderMap", orderMap);
				mav.addObject("OrderList", OrderList);
				mav.addObject("OrderVO", OrderVO);
				mav.setViewName(viewName);
				return mav;
			}else {
				String message = "회원 아이디로 로그인 해주시길 바랍니다"; 
				String viewName1 = "redirect:/main/main.do";
				mav.addObject(message);
				mav.setViewName(viewName1);
				
			}
			return mav;
		}

		@Override
		@RequestMapping(value ="/deleteOrder.do", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView deleteOrder(@RequestParam("o_id") int o_id,HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			ModelAndView mav = new ModelAndView();
			MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo");
			String _U_id = orderService.findU_id(o_id);
			if(memberInfo != null) {
				if(memberInfo.getU_id().equals(_U_id)) {
					orderService.deleteOrder(o_id);
					String message = "주문이 취소 되었습니다.";
					String viewName = "redirect:/order/selectUserOrders.do";
					mav.addObject(message);
					mav.setViewName(viewName);
					logger.info("뷰네임" + viewName);
					return mav;
					
				}else {
					String message = "본인의 주문내역만 취소 할 수 있습니다.";
					mav.addObject(message);
					String viewName1 = "/order/selectUserOrders.do";
					mav.setViewName(viewName1);
					return mav;
					
				}
				
			}else {
				String message = "로그인 정보 확인해주시기 바랍니다.";
				mav.addObject(message);
				String viewName2 = "/order/selectUserOrders.do";
				mav.setViewName(viewName2);
				return mav;
			}
		}
		@Override
		@RequestMapping(value ="/OrderForm.do", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView OrderForm(@RequestParam("g_id") int g_id,HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			ModelAndView mav = new ModelAndView();
			String viewName = (String) request.getAttribute("viewName");
			MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo");
			logger.info("뷰네임 :" + viewName);
			if (memberInfo != null) {
				GoodsVO goodsVO = goodsService.selectGoodsDetail(g_id);
				mav.setViewName(viewName);
				mav.addObject("goodsVO",goodsVO);
				return mav;
			} else {
				String viewName2 = "redirect:/Non/NonUser.do";
				String message = "로그인 해주시길 바랍니다";
				mav.setViewName(viewName2);
				mav.addObject(message);
			}
			return mav;
		
		}
		
		
}


