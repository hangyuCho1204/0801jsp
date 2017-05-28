package mypkg.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao extends SuperDao {
	
	public int InsertData( boardBean bean ){
		System.out.println( bean.toString() ); 
		String sql = " insert into boards(seq,subject,writer,wDate,contents) " ;
		sql += " values(myboard.nextval,?,?,to_date(?, 'yyyy/MM/dd'),?) " ;

		/*insert into boards(seq,subject,writer,wDate,contents) 
		values(myboard.nextval,'프레임워크','hong',to_date(20160101, 'yyyy/MM/dd'),'너무 잼있어요');
		*/
		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			
			pstmt.setString(1, bean.getSubject()) ;
			pstmt.setString(2, bean.getWriter()) ;
			pstmt.setString(3, bean.getwDate()) ;
			pstmt.setString(4, bean.getContents()) ;
			
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
	public int UpdateData( boardBean bean ){
		System.out.println( bean.toString() ); 
		String sql = " update boards set subject=?,writer=?,wDate=to_date(?, 'yyyy/MM/dd'),contents=? " ;
		sql += " where seq=? " ;
		/*update boards set subject='바뀐제목',writer='hong',wDate=to_date(20160101, 'yyyy/MM/dd'),contents='내용'
				where seq=1100;*/
		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;

			pstmt.setString(1, bean.getSubject()) ;
			pstmt.setString(2, bean.getWriter()) ;
			pstmt.setString(3, bean.getwDate()) ;
			pstmt.setString(4, bean.getContents()) ;
			pstmt.setInt(5, bean.getSeq());
			
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
	public int DeleteData( int pmkey ){
		String sql = " delete from boards where seq = ? " ;
		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			pstmt.setInt(1, pmkey);
			
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
	
	public BoardDao() {	}
	public List<boardBean> SelectDataList() {
		//모든 데이터를 조회한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from boards order by seq " ;
		List<boardBean> lists = new ArrayList<boardBean>();
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			pstmt = super.conn.prepareStatement(sql) ;			
			rs = pstmt.executeQuery() ;			
			while( rs.next() ){
				boardBean bean = new boardBean();
				bean.setSeq( rs.getInt("seq") );				
				bean.setSubject( rs.getString("subject") );
				bean.setWriter( rs.getString("writer") );
				bean.setwDate( rs.getString("wDate"));
				bean.setContents( rs.getString("contents") );			
				
				//날짜는 String.valueOf( rs.getDate() ) 의 형식
							 				 
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
	
	
	public boardBean SelectDataList(int pk) {
		//모든 데이터를 조회한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = " select * from boards where seq = ? " ;
		boardBean bean = null;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			pstmt.setInt(1, pk);
			rs = pstmt.executeQuery();
			while( rs.next() ){
				bean = new boardBean();
				bean.setSeq( rs.getInt("seq") );				
				bean.setSubject( rs.getString("subject") );
				bean.setWriter( rs.getString("writer") );
				bean.setwDate( rs.getString("wDate"));
				bean.setContents( rs.getString("contents") );			
				
				//날짜는 String.valueOf( rs.getDate() ) 의 형식
							 		
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
		
		return bean ;
	}

	public List<boardBean> SelectDataList(int beginRow, int endRow) {
		//랭킹을 이용하여 모든 데이터를 조회한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = "select seq, subject, writer, wDate, contents "; 
		sql += " from " ; 
		sql += " ( " ;
		sql += " select seq, subject, writer, wDate, contents, rank() over( order by seq desc ) as ranking " ;
		sql += " from boards  " ;
		sql += " ) " ;
		sql += " where ranking between ? and ? " ; 
		
		

		List<boardBean> lists = new ArrayList<boardBean>() ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, endRow); 
			
			rs = pstmt.executeQuery() ; 
			while ( rs.next() ) {
				boardBean bean = new boardBean() ; 
				bean.setSeq( rs.getInt("seq") );				
				bean.setSubject( rs.getString("subject") );
				bean.setWriter( rs.getString("writer") );		
				bean.setwDate( rs.getString("wDate"));
				bean.setContents( rs.getString("contents"));
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
}

