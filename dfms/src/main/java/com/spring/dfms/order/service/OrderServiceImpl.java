package com.spring.dfms.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dfms.order.dao.OrderDao;
import com.spring.dfms.goods.dto.GoodsDto;
import com.spring.dfms.member.dto.MemberDto;
import com.spring.dfms.order.dto.OrderDto;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public void addOrder(OrderDto orderDto, int point) throws Exception {
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("point"         , point);
		orderMap.put("orderGoodsQty" , orderDto.getOrderGoodsQty());
		orderMap.put("goodsCd"       , orderDto.getGoodsCd());
		orderMap.put("memberId"      , orderDto.getMemberId());
		
		orderDao.updateGoodsStockCnt(orderMap);
		orderDao.updateMemberPoint(orderMap);
		orderDao.insertOrder(orderDto);
	}

	@Override
	public MemberDto getOrdererDetail(String memberId) throws Exception {
		return orderDao.selectOneOrderer(memberId);
	}

	@Override
	public GoodsDto getGoodsDetail(int goodsCd) throws Exception {
		return orderDao.selectOneCartGoods(goodsCd);
	}

	@Override
	public List<GoodsDto> getGoodsListByCart(int[] goodsCdList) throws Exception {
		return orderDao.selectListCartGoods(goodsCdList);
	}

	@Override
	@Transactional
	public void addOrderByCart(Map<String, String> orderListMap) throws Exception {
		System.out.println("map");
		System.out.println(orderListMap);
		List<OrderDto> orderList = new ArrayList<OrderDto>();
		
		String[] temp1 = orderListMap.get("goodsCdList").split(","); 
		int[] goodsCdList = new int[temp1.length];
		for (int i = 0; i < temp1.length; i++) {
			goodsCdList[i] = Integer.parseInt(temp1[i]);
		}
		
		String[] temp2 = orderListMap.get("orderGoodsQtyList").split(",");
		int[] orderGoodsQtyList = new int[temp2.length];
		for (int i = 0; i < temp2.length; i++) {
			orderGoodsQtyList[i] = Integer.parseInt(temp2[i]);
		}
		
		String[] temp3 = orderListMap.get("paymentAmtList").split(",");
		int[] paymentAmtList = new int[temp3.length];
		for (int i = 0; i < temp3.length; i++) {
			paymentAmtList[i] = Integer.parseInt(temp3[i]);
		}
		
		
		for (int i = 0; i < goodsCdList.length; i++) {
			
			OrderDto orderDto = new OrderDto();
			orderDto.setGoodsCd(goodsCdList[i]);
			orderDto.setMemberId(orderListMap.get("memberId"));
			orderDto.setOrderGoodsQty(orderGoodsQtyList[i]);
			orderDto.setPaymentAmt(paymentAmtList[i]);
			orderDto.setOrdererNm(orderListMap.get("ordererNm"));
			orderDto.setOrdererHp(orderListMap.get("ordererHp"));
			orderDto.setZipcode(orderListMap.get("zipcode"));
			orderDto.setRoadAddress(orderListMap.get("roadAddress"));
			orderDto.setJibunAddress(orderListMap.get("jibunAddress"));
			orderDto.setNamujiAddress(orderListMap.get("namujiAddress"));
			orderDto.setDeliveryMethod(orderListMap.get("deliveryMethod"));
			orderDto.setDeliveryMessage(orderListMap.get("deliveryMessage"));
			orderDto.setPayMethod(orderListMap.get("payMethod"));
			orderDto.setPayOrdererHp(orderListMap.get("payOrdererHp"));
			orderDto.setCardCompanyNm(orderListMap.get("cardCompanyNm"));
			orderDto.setCardPayMonth(orderListMap.get("cardPayMonth"));
			orderList.add(orderDto);
			
		}
		
		String temp = orderListMap.get("totalPoint");
		int point = Integer.parseInt(temp);
		Map<String, Object> memberMap = new HashMap<String, Object>();
		memberMap.put("point", point);
		memberMap.put("memberId" , orderListMap.get("memberId"));
		orderDao.updateMemberPointByCart(memberMap); 

		
		List<Map<String,Integer>> goodsMapList = new ArrayList<Map<String,Integer>>();
		for (int i = 0; i < goodsCdList.length; i++) {
			Map<String,Integer> goodsMap = new HashMap<String, Integer>();
			goodsMap.put("goodsCd" , goodsCdList[i]);
			goodsMap.put("orderGoodsQty" , orderGoodsQtyList[i]);
			goodsMapList.add(goodsMap);
		}
		
		orderDao.updateGoodsStockCntByCart(goodsMapList); 
		orderDao.insertOrderByCart(orderList);
		
		String[] temp4 = orderListMap.get("cartCdList").split(",");
		int[] cartCdList = new int[temp4.length];
		for (int i = 0; i < temp4.length; i++) {
			cartCdList[i] = Integer.parseInt(temp4[i]);
		}
		
		orderDao.deleteCartByOrder(cartCdList);

	}

}
