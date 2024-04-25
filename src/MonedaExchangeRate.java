import java.util.Map;

public record MonedaExchangeRate(Map<String, Double>conversion_rates, Map<String, String> supported_codes, double conversion_result){
}
