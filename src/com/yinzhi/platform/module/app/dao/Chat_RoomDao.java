package com.yinzhi.platform.module.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.app.Chat_Room;

@Repository
public class Chat_RoomDao extends BaseDao {
	//实体字段
	private String field = this.getSelectField(Chat_Room.class);
	
	/**
	 * 返回用户实体信息
	 * @param userId
	 * @return
	 */
	public Chat_Room getChat_Room(String chat_roomId){
		return this.get(chat_roomId, Chat_Room.class);
	}
	
	/**
	 * 修改聊天室信息
	 * @param chat_Room
	 */
	public void updateChat_Room(Chat_Room chat_Room){
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		field.append(" room_id=?,room_name=?, updated_at=?,status=?,description=?,notice=?,notice_url=?,avatar=?,weight=?");
		List<Object> list = new ArrayList<Object>();
		list.add(chat_Room.getRoom_id());
		list.add(chat_Room.getRoom_name());
		list.add(chat_Room.getUpdated_at());
		list.add(chat_Room.getStatus());
		list.add(chat_Room.getDescription());
		list.add(chat_Room.getNotice());
		list.add(chat_Room.getNotice_url());
		list.add(chat_Room.getAvatar());
		list.add(chat_Room.getWeight());
		sql.append("update chat_room set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(chat_Room.getId());
		this.update(sql.toString(), list.toArray());
	}
	
	/**
	 * 全部资源列表
	 * @return
	 */
	public List<Chat_Room> getChat_RoomList(){
		String sql = "select " + field + " from chat_room order by room_id";
		return this.getList(sql, new Object[]{}, Chat_Room.class);
	}

	/**
	 * 禁用聊天室
	 * @param chat_roomId
	 */
	public void forbidden(String chat_roomId) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		Long updated_at = new Date().getTime();
		field.append(" updated_at="+updated_at+",status=3 ");
		List<Object> list = new ArrayList<Object>();
		
		sql.append("update chat_room set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(chat_roomId);  
		this.update(sql.toString(), list.toArray());
		
	}

	/**
	 * 启用聊天室
	 * @param chat_roomId
	 */
	public void release(String chat_roomId) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		Long updated_at = new Date().getTime();
		field.append(" updated_at="+updated_at+",status=0 ");
		List<Object> list = new ArrayList<Object>();
		
		sql.append("update chat_room set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(chat_roomId);  
		this.update(sql.toString(), list.toArray());
		
	}

	/**
	 * 保存聊天室
	 * @param chat_Room
	 */
	public void saveChat_Room(Chat_Room chat_Room) {
		this.insert(chat_Room);
	}

	/**
	 * 保存公告
	 * @param chat_Room
	 */
	public void addNotice(Chat_Room chat_Room) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		field.append("notice=?,notice_url=?");
		List<Object> list = new ArrayList<Object>();
		list.add(chat_Room.getNotice());
		list.add(chat_Room.getNotice_url());
		sql.append("update chat_room set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(chat_Room.getId());
		this.update(sql.toString(), list.toArray());
	}
	
	/**
	 * 获取分页信息
	 * @param chat_room
	 * @param start
	 * @param limit
	 * @return
	 */
	public Map<String, Object> getPage(Chat_Room chat_room, int start, int limit) {
		StringBuffer sql = new StringBuffer();
		sql.append("select " + field + " from chat_room order by id");
		List<Object> list = new ArrayList<Object>();
		return this.getPage(sql.toString(), list.toArray(), start, limit);
	}
	
	
}