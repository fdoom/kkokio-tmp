package org.likelion.kkokio.global.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // SECURITY
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),

    // ACCOUNT
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다."),
    DUPLICATED_USER(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다."),

    // STORE
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "식당 정보가 존재하지 않습니다."),

    STORE_USER_NOTMATH(HttpStatus.FORBIDDEN, "식당 가게 주인만 해당 정보를 수정 및 삭제하는 것이 가능합니다."),

    STORE_ALREADY_DELETED(HttpStatus.NOT_FOUND, "식당 정보는 이미 삭제되었습니다."),

    // CATEGORY
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "카테고리 정보가 존재하지 않습니다."),

    DUPLICATED_CATEGORYNAME(HttpStatus.CONFLICT, "카테고리 이름 정보가 중복되었습니다."),

    // MENU
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "메뉴 정보를 찾지 못했습니다."),


    // GPC CLOUD STORAGE
    GCP_IMAGE_UPLOAD_ERROR(HttpStatus.BAD_REQUEST, "해당 이미지는 GCP에 업로드가 불가능합니다."),

    // ORDER
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "주문 정보를 찾을 수 없습니다."),

    ORDER_NOT_FOUND_OR_NOT_UPDATED(HttpStatus.CONFLICT, "주문 정보가 존재하지 않거나 변경이 불가능합니다.")

    ;

    private final HttpStatus status;
    private final String message;
}