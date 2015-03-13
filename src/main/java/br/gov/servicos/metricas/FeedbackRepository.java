package br.gov.servicos.metricas;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface FeedbackRepository extends ElasticsearchRepository<Feedback, String> {
}
