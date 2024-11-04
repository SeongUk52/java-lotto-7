package lotto.service;

import static lotto.config.LottoConstants.LOTTO_END_NUMBER;
import static lotto.config.LottoConstants.LOTTO_NUMBER_COUNT;
import static lotto.config.LottoConstants.LOTTO_PRICE;
import static lotto.config.LottoConstants.LOTTO_START_NUMBER;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.config.ErrorMessage;
import lotto.config.LottoRule;
import lotto.model.Lotto;
import lotto.model.LottoReport;
import lotto.repository.LottoRepository;

public class LottoServiceImpl implements LottoService {
    private final LottoRepository lottoRepository;

    public LottoServiceImpl(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    @Override
    public void purchaseLotto(String purchaseAmount) {
        int numericPurchaseAmount = safeParsePurchaseAmount(purchaseAmount);

        lottoRepository.generateRandomLottos(numericPurchaseAmount);
    }

    @Override
    public List<String> generateLottoLogs() {
        return lottoRepository.findAll()
                .stream()
                .map(Lotto::toString)
                .toList();
    }

    @Override
    public LottoReport generateLottoReport(String purchaseAmount, String winningNumbers, String bonusNumber) {
        List<Integer> numericWinningNumbers = parseIntegerList(winningNumbers);
        int numericBonusNumber = Integer.parseInt(bonusNumber);

        validateLottoNumbers(numericWinningNumbers);
        validateLottoNumber(numericBonusNumber);

        List<LottoRule> lottoRuleList = lottoRepository.generatePrizeListBy(
                numericWinningNumbers, numericBonusNumber
        );

        Map<Object, Long> prizeCountMap = lottoRuleList
                .stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        List<String> winningReport = Arrays.stream(LottoRule.values())
                .filter(i -> i != LottoRule.NONE)
                .sorted(Comparator.reverseOrder())
                .map(i -> String.format("%d개 일치%s (%s원) - %d개",
                        i.getMatchCount(),
                        i.isHasBonus() ? ", 보너스 볼 일치" : "",
                        String.format("%,d", i.getPrize()),
                        prizeCountMap.getOrDefault(i, 0L)))
                .toList();

        double profitRate = (double) lottoRuleList
                .stream()
                .map(LottoRule::getPrize)
                .mapToInt(Integer::intValue)
                .sum()
                /
                safeParsePurchaseAmount(purchaseAmount);

        return new LottoReport(winningReport, profitRate);
    }

    private int safeParsePurchaseAmount(String stringInput) {
        validateNullInput(stringInput);
        try {
            int amount = Integer.parseInt(stringInput);
            validatePositive(amount);
            validateDivisibleByLottoPrice(amount);
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_NOT_NUMERIC.getMessage(), e);
        }
    }

    private List<Integer> parseIntegerList(String stringInput) {
        return Arrays.stream(stringInput.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    private void validateNullInput(String stringInput) {
        if (stringInput == null || stringInput.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_PURCHASE_AMOUNT_EMPTY.getMessage());
        }
    }

    private void validatePositive(int amount) {
        if (amount < LOTTO_PRICE.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_PURCHASE_AMOUNT_LACK.getMessage());
        }
    }

    private void validateDivisibleByLottoPrice(int amount) {
        if (amount % LOTTO_PRICE.getValue() != 0) {
                throw new IllegalArgumentException(
                        ErrorMessage.INVALID_INPUT_PURCHASE_AMOUNT_CANNOT_DIVIDE.getMessage()
                );
        }
    }

    private void validateLottoNumbers(List<Integer> numbers) {
        validateSize(numbers);
        numbers.forEach(this::validateLottoNumber);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_LOTTO_COUNT.getMessage());
        }
    }

    private void validateLottoNumber(Integer number) {
        if (number < LOTTO_START_NUMBER.getValue() || number > LOTTO_END_NUMBER.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_LOTTO_RANGE.getMessage());
        }
    }
}
