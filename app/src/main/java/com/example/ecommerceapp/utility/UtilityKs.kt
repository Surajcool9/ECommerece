package com.example.ecommerceapp.utility

import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout

class UtilityKs {
    companion object {

        fun startShimmer(shimmer: ShimmerFrameLayout) {
            shimmer.visibility = View.VISIBLE
            shimmer.startShimmer()
        }

        fun stopShimmer(shimmer: ShimmerFrameLayout) {
            shimmer.visibility = View.GONE
            shimmer.stopShimmer()
        }
    }
}