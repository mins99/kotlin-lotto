package lottery.domain

data class WinningLotto(
    val winningLotto: Lotto,
    val bonusNumber: LottoNumber
) {
    init {
        require(!winningLotto.lottoNumber.contains(bonusNumber)) { "로또 당첨번호와 보너스 번호는 달라야 합니다" }
    }

    constructor(numbers: List<Int>, bonusNumber: Int) : this(
        Lotto.of(numbers),
        LottoNumber(bonusNumber)
    )

    fun getMatchResult(other: Lotto): Int {
        return other.lottoNumber.intersect(winningLotto.lottoNumber.toSet()).size
    }

    fun getBonusMatchResult(other: Lotto): Boolean {
        return other.lottoNumber.contains(bonusNumber)
    }
}