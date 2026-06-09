package de.moshplaner.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/data")
class DataController(
    private val dataService: DataService
) {
    @GetMapping
    fun getData(): List<ExternalDataEntity> {
        return dataService.getAllData()
    }
}
