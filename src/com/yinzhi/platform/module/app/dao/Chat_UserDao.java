package com.yinzhi.platform.module.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.app.Chat_User;
@Repository
public class Chat_UserDao extends BaseDao {
	//实体字段
	private String field = this.getSelectField(Chat_User.class);
	
	/**
	 * 返回用户实体信息
	 * @param userId
	 * @return
	 */
	public Chat_User getChat_User(String userId){
		return this.get(userId, Chat_User.class);
	}
	
	
	/**
	 * 全部资源列表
	 * @return
	 */
	public List<Chat_User> getChat_UserList(){
		String sql = "select " + field + " from chat_user order by user_id";
		return this.getList(sql, new Object[]{}, Chat_User.class);
	}

	/**
	 * 用户分页列表
	 * @param user
	 * @param start
	 * @param limit
	 * @return
	 */
	public Map<String, Object> getPage(String chat_roomId, String nick, String status, int start, int limit) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select u.id,u.room,u.created_at,u.updated_at,u.status,u.nick,u.online_status,r.room_name from chat_user u, chat_room r");
		
		StringBuffer where = new StringBuffer();
		where.append(" where room_id in ('" + chat_roomId + "') and u.room=r.room_id");
		List<Object> list = new ArrayList<Object>();
		if(!"".equals(nick) && null != nick){
			where.append(" and u.nick like ?");
			list.add(nick + "%");
		}
		if(!"".equals(status) && null != status){
			if(status.equals("3")) {
				where.append(" and u.status = ?");
				list.add(status);
			}
		}
		sql.append(where).append(" order by u.created_at desc");
		return this.getPage(sql.toString(), list.toArray(), start, limit);
	}
	
	/**
	 * 禁用用户
	 * @param userId
	 */
	public void forbid(String userId) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		Long updated_at = new Date().getTime();
		field.append(" updated_at="+updated_at+",status=3 ");
		List<Object> list = new ArrayList<Object>();
		
		sql.append("update chat_user set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(userId);  
		this.update(sql.toString(), list.toArray());
		
	}
	
	/**
	 * 解禁用户
	 * @param userId
	 */
	public void release(String userId) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		Long updated_at = new Date().getTime();
		field.append(" updated_at="+updated_at+",status=0 ");
		List<Object> list = new ArrayList<Object>();
		
		sql.append("update chat_user set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(userId);  
		this.update(sql.toString(), list.toArray());
		
	}
}