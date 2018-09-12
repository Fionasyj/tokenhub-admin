package com.yinzhi.platform.module.app.service;

import java.util.List;
import java.util.Map;
import com.yinzhi.platform.entity.app.Chat_Room;
public interface IChat_RoomService {
	
	/**
	 * 返回聊天室实体信息
	 * @param chat_roomId
	 * @return
	 */
	Chat_Room getChat_Room(String chat_roomId);
	
	/**
	 * 保存聊天室
	 * @param chat_Room
	 */
	void saveChat_Room(Chat_Room chat_Room);
	
	/**
	 * 修改聊天室
	 * @param user
	 */
	void updateChat_Room(Chat_Room chat_Room);
	
	/**
	 * 聊天室分页
	 * @param user
	 * @param start
	 * @param limit
	 * @return
	 */
	Map<String, Object> getPage(Chat_Room chat_room, int start, int limit);
	
	
	/**
	 * 获取聊天室列表
	 * @return
	 */
	List<Chat_Room> getChat_RoomList();

	/**
	 * 禁用聊天室
	 */
	void forbidden(String chat_roomId);

	/**
	 * 启用聊天室
	 */
	void release(String chat_roomId);
	
	/**
	 * 保存公告
	 * @param chat_Room
	 */
	void addNotice(Chat_Room chat_Room);
}
