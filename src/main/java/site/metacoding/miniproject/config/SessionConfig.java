// package site.metacoding.miniproject.config;

// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;

// import javax.servlet.annotation.WebListener;
// import javax.servlet.http.HttpSessionListener;

// 웹 리스너 사용해서 중복로그인 체크
// @WebListener
// public class SessionConfig implements HttpSessionListener {

//     private static final Map<String, Integer> loginUserIdList = new ConcurrentHashMap<>();

//     public synchronized static Integer getSessionidCheck(Integer userinfo) {
//         Integer result = null;
//         for (String id : loginUserIdList.keySet()) {
//             if (loginUserIdList.get(id) == userinfo) {
//                 result = userinfo;
//             }
//         }
//         return result;
//     }

//     public static void logout(String usersId) {
//         loginUserIdList.remove(usersId);

//     }

//     public static void login(String usersInfo, Integer usersId) {
//         loginUserIdList.put(usersInfo, usersId);
//     }

// }
