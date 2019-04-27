package ufrj.devmob.votadevmob.result

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler

class PollResultValueFormatter: IValueFormatter {

    override fun getFormattedValue(value: Float, entry: Entry?, dataSetIndex: Int, viewPortHandler: ViewPortHandler?): String {
        entry as PieEntry
        return "${entry.label} (${value.toInt()})"
    }
}