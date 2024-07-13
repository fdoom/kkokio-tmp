package org.likelion.kkokio.domain.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.order.dto.request.OrderInfoRequestDTO;
import org.likelion.kkokio.domain.order.dto.response.OrderTimeResponseDTO;
import org.likelion.kkokio.domain.order.dto.response.OrderInfoResponseDTO;
import org.likelion.kkokio.domain.order.service.OrderService;
import org.likelion.kkokio.global.base.exception.ErrorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "빈칸이 존재하거나 잘못된 요청을 했습니다.",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "인증 정보가 존재하지 않습니다.",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다.",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "해당 내용이 존재하지 않습니다.",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
})
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/info/{storeId}")
    @Operation(summary = "주문 등록", description = "주문 정보 등록")
    @Parameter(name = "storeId", description = "가게 ID값")
    public ResponseEntity<OrderInfoResponseDTO> createOrderInfo(
            @PathVariable("storeId") Long storeId,
            @Valid @RequestBody List<OrderInfoRequestDTO> orderInfoRequestDTOList
    ) {
        return orderService.createOrderInfo(storeId, orderInfoRequestDTOList);
    }

    @PatchMapping("/info/{orderId}")
    @Operation(summary = "주문 변경", description = "등록된 주문의 내용을 변경")
    @Parameter(name = "orderId", description = "주문 ID값")
    public ResponseEntity<OrderInfoResponseDTO> updateOrderInfo(
            @PathVariable("orderId") Long orderId,
            @Valid @RequestBody List<OrderInfoRequestDTO> orderInfoRequestDTO) {
        return orderService.updateOrderInfo(orderId, orderInfoRequestDTO);
    }

    @PatchMapping("/cook/{orderId}")
    @Operation(summary = "주문 요리 시작", description = "등록된 주문에 대한 요리 시작 정보 등록")
    @Parameter(name = "orderId", description = "주문 ID값")
    public ResponseEntity<OrderTimeResponseDTO> cookOrderInfo(@PathVariable("orderId") Long orderId) {
        return orderService.cookOrderInfo(orderId);
    }

    @DeleteMapping("/info/{orderId}")
    @Operation(summary = "주문 삭제", description = "등록된 주문 정보 삭제")
    @Parameter(name = "orderId", description = "주문 ID값")
    public ResponseEntity<Void> deleteOrderInfo(@PathVariable("orderId") Long orderId) {
        return orderService.deleteOrderInfo(orderId);
    }

    @PatchMapping("/finish/{orderId}")
    @Operation(summary = "주문 요리 완료", description = "등록된 주문에 대한 요리 완료 및 서빙 시작 시간 등록")
    @Parameter(name = "orderId", description = "주문 ID값")
    public ResponseEntity<OrderTimeResponseDTO> finishOrderInfo(@PathVariable("orderId") Long orderId) {
        return orderService.finishOrderInfo(orderId);
    }

    @GetMapping("/info/{orderId}")
    @Operation(summary = "주문 조회", description = "특정 주문 정보 조회")
    @Parameter(name = "orderId", description = "주문 ID값")
    public ResponseEntity<OrderInfoResponseDTO> getOrderInfoByOrderId(@PathVariable("orderId") Long orderId) {
        return orderService.getOrderInfoByOrderId(orderId);
    }

    @GetMapping("/info/store/{storeId}")
    @Operation(summary = "주문 조회", description = "가게 ID를 기반으로 주문 정보 조회")
    @Parameters(value = {
            @Parameter(name = "storeId", description = "가게 ID값"),
            @Parameter(name = "pageable", description = "페이징 정보, 기본설정: orderId 값을 기준으로 오름차순")
    })
    public ResponseEntity<Page<OrderInfoResponseDTO>> getOrderInfoByStoreId(@PathVariable Long storeId, @PageableDefault Pageable pageable) {
        return orderService.getOrderInfoByStoreId(storeId, pageable);
    }

    @GetMapping("/info/store/{storeId}/category/{categoryId}")
    @Operation(summary = "주문 조회", description = "가게 ID와 카테고리 ID값을 기반으로 주문 정보 조회")
    @Parameters(value = {
            @Parameter(name = "storeId", description = "가게 ID값"),
            @Parameter(name = "categoryId", description = "카테고리 ID값"),
            @Parameter(name = "pageable", description = "페이징 정보, 기본설정: orderId 값을 기준으로 오름차순")
    })
    public ResponseEntity<Page<OrderInfoResponseDTO>> getOrderInfoByStoreIdAndCategoryId(
            @PathVariable("storeId") Long storeId,
            @PathVariable("categoryId") Long categoryId,
            @PageableDefault(sort = "orderId") Pageable pageable
    ) {
        return orderService.getOrderInfoByStoreIdAndCategoryId(storeId, categoryId, pageable);
    }
}
