package board.command;

import javax.servlet.http.*;

public interface MembmerBoardCmd {
	
	public void execute(HttpServletRequest request, HttpServletResponse response);

}
