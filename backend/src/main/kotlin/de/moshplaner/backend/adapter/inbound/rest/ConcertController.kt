package de.moshplaner.backend.adapter.inbound.rest

import de.moshplaner.backend.domain.ports.inbound.ConcertAccess
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/concerts")
class ConcertController(
    private val concertAccess: ConcertAccess,
) {
    @PostMapping
    fun createConcert(
        @RequestBody request: ConcertRequest,
    ): ResponseEntity<ConcertResponse> {
        val concert = concertAccess.createConcert(request.name, request.venue, request.date)
        return ResponseEntity.status(HttpStatus.CREATED).body(ConcertResponse.fromDomain(concert))
    }

    @GetMapping
    fun getAllConcerts(): ResponseEntity<List<ConcertResponse>> =
        ResponseEntity.ok(concertAccess.getAllConcerts().map { ConcertResponse.fromDomain(it) })
}
