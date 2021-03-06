package Logic;

import android.content.Context;

import java.util.concurrent.CountDownLatch;

import DAO.TodoItemDAO;
import DAO.TodoItemPHP;
import model.TodoItem;

public class TodoDeleteLogic {
    public boolean execute(int todoId, Context context) {
        boolean isLogic;
        //データベースにTODOを登録
        //SQLITE
        /*TodoItemDAO dao = new TodoItemDAO(context);
        boolean isLogic = dao.delete(todoId);*/
        //MYSQL
        TodoItem todoItem = new TodoItem(todoId);
        CountDownLatch latch = new CountDownLatch(1);
        TodoItemPHP todoItemPHP = new TodoItemPHP(todoItem,null,latch);
        todoItemPHP.execute("http://androidtest.php.xdomain.jp/DeleteTodo.php",TodoItemPHP.DELETE_TODO);
        try {
            latch.await();
            isLogic = todoItemPHP.getResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        //結果を返す
        return isLogic;
    }
}
