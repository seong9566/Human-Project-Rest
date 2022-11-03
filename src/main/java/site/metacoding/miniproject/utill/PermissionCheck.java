package site.metacoding.miniproject.utill;

import site.metacoding.miniproject.exception.ApiException;

public class PermissionCheck {

    public static class UserPermissionCheck{
        public static void permissionCheck(Integer permissionId, Integer targetId){
            if (permissionId != targetId) {
                throw new ApiException("해당유저의 권한이 없습니다.");
            }
        }
    }
    
}
