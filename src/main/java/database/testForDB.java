package database;

public class testForDB {

	public static void main(String[] args) {
		Database db = new Database();
		
		/* ---------- db 문법 설명 ----------
		 * 삽입 : insert ('이름',학번,'단과대학','학과(부)')
		 * 예) String sql = "insert ('차영준',20172560,'소프트웨어대학','컴퓨터공학부')";
		 * 
		 * 선택 : select 컬럼=값
		 * String sql = "select name='차영준'";
		 * String sql = "select"; 인자가 없으므로 전체 검색
		 * 
		 * 수정 : update 바꿀컬럼=값 where 검색컬럼=값 
		 * String sql = "update name='영준차' where studNum=20172560;"
		 * 
		 * 삭제 : delete 컬럼=값
		 * String sql = "delete name='영준차'";
		 * 
		 * 이름,단과대학,학과(부) => 문자열
		 * 학번 => 정수형
		 * (문자열과 정수형은 작은 따음표로 구분한다.)
		 * ---------- db 문법 설명 ---------- */
	}
}
