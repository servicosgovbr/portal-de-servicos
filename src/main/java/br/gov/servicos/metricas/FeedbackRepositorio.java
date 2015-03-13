package br.gov.servicos.metricas;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface FeedbackRepositorio extends ElasticsearchRepository<Feedback, String> {
}
