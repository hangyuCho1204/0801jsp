package mypkg.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends SuperDao {
	public int InsertData( Member bean ){
		System.out.println( bean.toString() ); 
		String sql = " insert into members(id, name, password,salary, hiredate,                gender,hobby,job, zipcode,address1,  address2,mpoint) " ; 
		sql += " values(                 ?, ?,    ?,       ?,    to_date(?, 'yyyy/MM/dd'),   ?,    ?,    ?,  ?,      ?,   ?,    ? ) " ;

		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			//개발자가 수정할 곳 2 : ? 수정할 것
			pstmt.setString(1, bean.getId() );
			pstmt.setString(2, bean.getName() );
			pstmt.setString(3, bean.getPassword());
			pstmt.setInt(4, bean.getSalary() );
			pstmt.setString(5, bean.getHiredate() );
			pstmt.setString(6, bean.getGender() );
			pstmt.setString(7, bean.getHobby() );
			pstmt.setString(8, bean.getJob());
			pstmt.setString(9, bean.getZipcode() );
			pstmt.setString(10, bean.getAddress1());
			pstmt.setString(11, bean.getAddress2() );
			pstmt.setInt(12, bean.getMpoint() );
			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
		} catch (Exception e) {
			SQLException err = (SQLException)e ;
			//getErrorCode() : 오라클 오류 상수가 리턴
			//예 : not null 이면 1400 
			cnt = - err.getErrorCode() ;			
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally{
			try {
				if( pstmt != null ){ pstmt.close(); }
				super.closeConnection(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt ;
	}
	public int UpdateData( Member bean ){
		System.out.println( bean.toString() ); 
		String sql = " update members set ";
		sql += " mpoint=?, name=?, password=?, salary=?, hiredate=to_date(?, 'yyyy/MM/dd'), " ;    
		sql += " gender=?, hobby=?, job=?, zipcode=?, address1=?, address2=?  " ;
		sql += " where id = ? " ; 
		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			//개발자가 수정할 곳 2 : ? 수정할 것
			pstmt.setString(12, bean.getId());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPassword());			
			pstmt.setInt(4, bean.getSalary());			
			pstmt.setString(5, bean.getHiredate());			
			pstmt.setString(6, bean.getGender());
			pstmt.setString(7, bean.getHobby());
			pstmt.setString(8, bean.getJob());
			pstmt.setString(9, bean.getZipcode());
			pstmt.setString(10, bean.getAddress1());
			pstmt.setString(11, bean.getAddress2());
			pstmt.setInt(1, bean.getMpoint());
			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
		} catch (Exception e) {
			SQLException err = (SQLException)e ;			
			cnt = - err.getErrorCode() ;			
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally{
			try {
				if( pstmt != null ){ pstmt.close(); }
				super.closeConnection(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt ;
	}
	public int DeleteData( String pmkey ){		
		String sql = " delete from members where id = ? " ;
		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			pstmt.setString(1, pmkey);			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
		} catch (Exception e) {
			SQLException err = (SQLException)e ;			
			cnt = - err.getErrorCode() ;			
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally{
			try {
				if( pstmt != null ){ pstmt.close(); }
				super.closeConnection(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt ;
	}
	
	public List<Member> SelectDataList() {
		//모든 데이터를 조회한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from members order by name " ;
		List<Member> lists = new ArrayList<Member>();
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			pstmt = super.conn.prepareStatement(sql) ;			
			rs = pstmt.executeQuery() ;			
			while( rs.next() ){
				Member bean = new Member();
				bean.setAddress1( rs.getString("address1") );
				bean.setAddress2( rs.getString("address2") );
				bean.setGender( rs.getString("gender") );
				String[] hobby = rs.getString("hobby").split(",");
				bean.setHobby(hobby);
				bean.setId( rs.getString("id") );
				bean.setJob( rs.getString("job") );
				bean.setZipcode( rs.getString("zipcode") );
				bean.setPassword( rs.getString("password") );
				bean.setName( rs.getString("name") );
				
				//날짜는 String.valueOf( rs.getDate() ) 의 형식
				bean.setHiredate( String.valueOf(rs.getDate("hiredate")) );
				
				//숫자는 Integer.parsteInt()
				bean.setMpoint( Integer.parseInt(rs.getString("mpoint")) );
				bean.setSalary( Integer.parseInt(rs.getString("salary")) );
							 				 
				lists.add( bean ) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if( rs != null ){ rs.close(); }
				if( pstmt != null ){ pstmt.close(); }
				super.closeConnection(); 
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		
		return lists ;
	}
	public MemberDao() {
		
	}
	//특정 회원에 상세 정보 보기
	public Member SelectDataByPk( String id ){
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				
		String sql = "select * " ;
		sql += " from members " ; 
		sql += " where id = ? " ;
		Member bean = null ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;			
			//placehodelr 치환은 반드시 execute 메소드 실행 바로 직전에 하세요. 
			pstmt.setString( 1, id   ); 
			rs = pstmt.executeQuery() ;
			
			if ( rs.next() ) {
				//1 건이 발견됨
				bean = new Member() ; 
				bean.setId( rs.getString("id") );
				bean.setName( rs.getString("name") );
				bean.setPassword( rs.getString("password") );
				bean.setSalary( rs.getInt("salary") );
				bean.setHiredate( String.valueOf( rs.getDate("hiredate"))) ;
				bean.setGender( rs.getString("gender") );
				String[] hobby = rs.getString("hobby").split(",");
				bean.setHobby(hobby);
				bean.setJob( rs.getString("job") );
				bean.setZipcode( rs.getString("zipcode") );
				bean.setAddress1( rs.getString("address1") ); 
				bean.setAddress2( rs.getString("address2") ); 
				bean.setMpoint( rs.getInt("mpoint") );
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				if( rs != null){ rs.close(); } 
				if( pstmt != null){ pstmt.close(); } 
				this.closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		} 		
		return bean  ;
	}			
	
	//회원 가입 시 아이디 중복 체크
		public boolean checkId( String id ){
			PreparedStatement pstmt = null ;
			ResultSet rs = null ;				
			String sql = "select * " ;
			sql += " from members " ; 
			sql += " where id = ? " ;
			boolean idcheck = false ;
			try {
				if( this.conn == null ){ this.conn = this.getConnection() ; }			
				pstmt = this.conn.prepareStatement(sql) ;			
				//placehodelr 치환은 반드시 execute 메소드 실행 바로 직전에 하세요. 
				pstmt.setString( 1, id   ); 
				rs = pstmt.executeQuery() ;
				
				
				if ( rs.next() ) {
					//1 건이 발견됨
					
					String originId = rs.getString("id");
					
					idcheck = originId.equals(id);
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				try {
					if( rs != null){ rs.close(); } 
					if( pstmt != null){ pstmt.close(); } 
					this.closeConnection() ;
				} catch (Exception e2) {
					e2.printStackTrace(); 
				}
			} 		
			return idcheck;
		}			


public List<Member> SelectDataList(int beginRow, int endRow) {
	//랭킹을 이용하여 모든 데이터를 조회한다.
	PreparedStatement pstmt = null ;
	ResultSet rs = null ;
	
	String sql = "select id, name, password, salary, hiredate, gender, hobby,job, zipcode, address1, address2, mpoint, ranking "; 
	sql += " from " ; 
	sql += " ( " ;
	sql += "  select id, name, password, salary, hiredate, gender, hobby,job, zipcode, address1, address2, mpoint, rank() over( order by id desc )  as ranking " ;
	sql += " from members  " ;
	sql += " ) " ;
	sql += " where ranking between ? and ? " ;
	
	
	List<Member> lists = new ArrayList<Member>() ;
	try {
		if( this.conn == null ){ this.conn = this.getConnection() ; }			
		pstmt = this.conn.prepareStatement(sql) ;
		pstmt.setInt(1, beginRow);
		pstmt.setInt(2, endRow); 
		
		rs = pstmt.executeQuery() ; 
		while ( rs.next() ) {
			Member bean = new Member() ; 
			bean.setId( rs.getString("id") );
			bean.setName( rs.getString("name") );
			bean.setPassword( rs.getString("password") );
			bean.setSalary( rs.getInt("salary") );
			bean.setHiredate( String.valueOf( rs.getDate("hiredate"))) ;
			bean.setGender( rs.getString("gender") );
			String[] hobby = rs.getString("hobby").split(",");
			bean.setHobby(hobby);
			bean.setJob( rs.getString("job") );
			bean.setZipcode( rs.getString("zipcode") );
			bean.setAddress1( rs.getString("address1") ); 
			bean.setAddress2( rs.getString("address2") ); 
			bean.setMpoint( rs.getInt("mpoint") ); 			
			lists.add( bean ) ; 
		}
		
	} catch (SQLException e) {			
		e.printStackTrace();
	} finally{
		try {
			if( rs != null){ rs.close(); } 
			if( pstmt != null){ pstmt.close(); } 
			this.closeConnection() ;
		} catch (Exception e2) {
			e2.printStackTrace(); 
		}
	} 		
	return lists  ;
}

public int SelectTotalCount() {
	PreparedStatement pstmt = null ;
	ResultSet rs = null;
	String sql = " select count(*) as cnt from members " ;
	
	int cnt = 0 ; //없는 경우의 기본 값
	try {
		if( this.conn == null ){ this.conn = this.getConnection() ; }
		pstmt = this.conn.prepareStatement( sql ) ;
		rs = pstmt.executeQuery() ; 
		
		if( rs.next() ){
			cnt = rs.getInt("cnt");
		}
	} catch (Exception e) {
		e.printStackTrace();
		try {
			conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	} finally{
		try {
			if( rs != null ){ rs.close(); }
			if( pstmt != null ){ pstmt.close(); }
			this.closeConnection();
		} catch (Exception e2) {
			e2.printStackTrace(); 
		}
	}
	return cnt;
}

/*public List<Zipcode> SelectDataZipcode(String dong) {
	PreparedStatement pstmt = null ;
	ResultSet rs = null ;
	String sql = "select * from zipcodes " ;
	sql += " where dong like '" + dong + "%'" ;
	sql += " order by sido asc, gugun asc, dong asc " ; 
	List<Zipcode> lists = new ArrayList<Zipcode>();
	try {
		if( conn == null ){ super.conn = super.getConnection() ; }
		pstmt = super.conn.prepareStatement(sql) ;			
		 
		rs = pstmt.executeQuery() ;			
		while( rs.next() ){
			Zipcode bean = new Zipcode();
			bean.setSido( rs.getString("sido") );
			bean.setBunji( rs.getString("bunji") );
			bean.setDong( rs.getString("dong") );
			bean.setGugun( rs.getString("gugun") );
			bean.setZipcode( rs.getString("zipcode") );
			bean.setSeqnum( Integer.parseInt( rs.getString("seqnum") ));
			 
			lists.add( bean ) ;
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally{
		try {
			if( rs != null ){ rs.close(); }
			if( pstmt != null ){ pstmt.close(); }
			super.closeConnection(); 
		} catch (Exception e2) {
			e2.printStackTrace(); 
		}
	}		
	return lists ;
}	*/

}