package com.fintech.korona.tips.presentation

import com.fintech.korona.tips.AppRouter
import com.fintech.korona.tips.data.model.objects.Card
import com.fintech.korona.tips.interactors.CardInteractor
import com.fintech.korona.tips.view.home.HomeView
import com.fintech.korona.tips.view.scanner.ScannerFragment

/**
 * @author Ivan Prokopyev
 */
class HomePresenter(private val view: HomeView,
                    private val router: AppRouter) : BasePresenter<HomeView>() {

    private val cardInteractor = CardInteractor()
    private lateinit var card: Card

    override fun onViewAttach() {
        super.onViewAttach()
        card = cardInteractor.getCard()

        view.showCard(card)
    }


    fun onScanButtonClick() {
        router.routeTo(ScannerFragment.newInstance())
    }

    fun onSaveCardsClick(cardNumber: String, cardHolder: String, cardDate: String, cardCvv: String) {
        if (cardNumber != "" &&
                cardHolder != "" &&
                cardDate != "") {
            card = Card(number = cardNumber, holderName = cardHolder, date = cardDate)
            cardInteractor.saveCard(card)
        } else {
            view.showMessage("Введите данные карты!")
        }
    }
}