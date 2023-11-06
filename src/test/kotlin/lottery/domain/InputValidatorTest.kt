package lottery.domain

import io.kotest.matchers.shouldBe
import lottery.validator.InputValidator
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class InputValidatorTest {

    @Test
    @DisplayName("로또 구입 금액이 숫자가 아니면 구매가 불가능하다")
    fun validateAmount() {
        Assertions.assertThatThrownBy {
            InputValidator.validateAmount("lotto")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("유효한 지난 주 당첨 번호를 입력하면 리스트를 반환한다")
    fun validateWinningNumbers() {
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 6)
        val result = InputValidator.validateWinningNumbers("1,2,3,4,5,6")

        result.shouldBe(lottoNumbers)
    }

    @Test
    @DisplayName("지난 주 당첨 번호가 하나라도 숫자가 아니면 오류를 반환한다")
    fun invalidWinningNumbers() {
        Assertions.assertThatThrownBy {
            InputValidator.validateWinningNumbers("a,2,3,4,5,6")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("지난 주 당첨 번호가 하나라도 음수 번호를 가지면 오류를 반환한다")
    fun invalidWinningNumbers2() {
        Assertions.assertThatThrownBy {
            InputValidator.validateWinningNumbers("-1,2,3,4,5,6")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}