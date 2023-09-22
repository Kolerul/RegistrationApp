package com.example.registrationapp.ui.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.BounceInterpolator


fun View.shakeAnimation() {
    val toRight = ObjectAnimator.ofFloat(
        this, View.TRANSLATION_X, 100f
    ).apply {
        duration = 250L
    }

    val toLeft = ObjectAnimator.ofFloat(
        this, View.TRANSLATION_X, -100f
    ).apply {
        duration = 250L
    }

    val toMiddle = ObjectAnimator.ofFloat(
        this, View.TRANSLATION_X, 0f
    ).apply {
        duration = 550L
        interpolator = BounceInterpolator()
    }

    val animatorSet = AnimatorSet()
    animatorSet.play(toLeft)
        .after(toRight)
        .before(toMiddle)
    animatorSet.start()

}