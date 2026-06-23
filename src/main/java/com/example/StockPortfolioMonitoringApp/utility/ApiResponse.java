package com.example.StockPortfolioMonitoringApp.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //internally contains @Getter,@Setter,@ToString,@EqualsAndHashCode,@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T>{

    private boolean success;

    private String message;

    private T data;
}
