package de.moshplaner.backend

import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod

@Service
class DataService(
    private val repository: ExternalDataRepository
) {
    private val logger = LoggerFactory.getLogger(DataService::class.java)

    @PostConstruct
    fun fetchAndPersistData() {
        logger.info("Fetching data from external API...")
        try {
            val restTemplate = RestTemplate()
            val url = "https://jsonplaceholder.typicode.com/posts"
            
            // Using a parameterized type reference to get a List
            val response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<ExternalDataEntity>>() {}
            )
            
            val data = response.body
            if (data != null) {
                // Save first 10 items for demo purposes
                val itemsToSave = data.take(10)
                repository.saveAll(itemsToSave)
                logger.info("Successfully persisted ${itemsToSave.size} items to PostgreSQL.")
            }
        } catch (e: Exception) {
            logger.error("Failed to fetch or persist external data: ${e.message}")
        }
    }

    fun getAllData(): List<ExternalDataEntity> {
        return repository.findAll()
    }
}
