package reconnect.server.domain.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reconnect.server.domain.report.model.request.ReportRequest;
import reconnect.server.domain.report.model.response.ReportResponse;
import reconnect.server.domain.report.repository.ReportRepository;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.global.model.entity.mysql.Report;
import reconnect.server.domain.missing_person.repository.MissingPersonRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final MissingPersonRepository missingPersonRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, MissingPersonRepository missingPersonRepository) {
        this.reportRepository = reportRepository;
        this.missingPersonRepository = missingPersonRepository;
    }

    public ReportResponse createReport(ReportRequest reportRequest) {
        MissingPerson missingPerson = missingPersonRepository.findById(reportRequest.getMissingPersonId())
                .orElseThrow(() -> new RuntimeException("MissingPerson not found"));

        // Report entity 생성
        Report report = mapToEntity(reportRequest, missingPerson);
        reportRepository.save(report);

        // reportCount 업데이트
        missingPerson.setReportCount(missingPerson.getReportCount() + 1);
        missingPersonRepository.save(missingPerson);

        return mapToResponse(report);
    }

    private Report mapToEntity(ReportRequest request, MissingPerson missingPerson) {
        return Report.builder()
                .missingPerson(missingPerson)
                .gender(request.getGender())
                .ageGroup(request.getAgeGroup())
                .specialFeature(request.getSpecialFeature())
                .clothingItems(request.getClothingItems())
                .foundImageUrl(request.getFoundImageUrl())
                .locationFound(request.getLocationFound())
                .additionalDescription(request.getAdditionalDescription())
                .surroundingImageUrl(request.getSurroundingImageUrl())
                .additionalReport(request.getAdditionalReport())
                .reportedAt(request.getReportedAt())
                .reportCount(missingPerson.getReportCount() + 1)  // 추가된 count
                .build();
    }

    private ReportResponse mapToResponse(Report report) {
        return ReportResponse.builder()
                .reportId(report.getReportId())
                .missingPersonId(report.getMissingPerson().getId())
                .gender(report.getGender())
                .ageGroup(report.getAgeGroup())
                .specialFeature(report.getSpecialFeature())
                .clothingItems(report.getClothingItems())
                .foundImageUrl(report.getFoundImageUrl())
                .locationFound(report.getLocationFound())
                .additionalDescription(report.getAdditionalDescription())
                .surroundingImageUrl(report.getSurroundingImageUrl())
                .additionalReport(report.getAdditionalReport())
                .reportedAt(report.getReportedAt())
                .reportCount(report.getReportCount())
                .build();
    }
}
