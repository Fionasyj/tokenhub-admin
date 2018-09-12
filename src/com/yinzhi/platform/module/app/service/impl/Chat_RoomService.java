package com.yinzhi.platform.module.app.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yinzhi.platform.entity.app.Chat_Room;
import com.yinzhi.platform.module.app.dao.Chat_RoomDao;
import com.yinzhi.platform.module.app.service.IChat_RoomService;

@Service
public class Chat_RoomService implements IChat_RoomService{
	
	@Autowired
	private Chat_RoomDao chat_roomDao;



	public Chat_Room getChat_Room(String chat_roomId) {
		return this.chat_roomDao.getChat_Room(chat_roomId);
	}

	public void updateChat_Room(Chat_Room chat_Room) {
		chat_Room.setCreated_at(new Date().getTime());
		this.chat_roomDao.updateChat_Room(chat_Room);
	}

	@Override
	public Map<String, Object> getPage(Chat_Room chat_room, int start, int limit) {
		return this.chat_roomDao.getPage(chat_room, start, limit);
	}

	@Override
	public List<Chat_Room> getChat_RoomList() {
		return this.chat_roomDao.getChat_RoomList();

	}

	@Override
	public void forbidden(String chat_roomId) {
		this.chat_roomDao.forbidden(chat_roomId);
		
	}

	@Override
	public void release(String chat_roomId) {
		this.chat_roomDao.release(chat_roomId);
		
	}

	@Override
	public void saveChat_Room(Chat_Room chat_Room) {
		this.chat_roomDao.saveChat_Room(chat_Room);
	}


	@Override
	public void addNotice(Chat_Room chat_Room) {
		this.chat_roomDao.addNotice(chat_Room);
	}

}
