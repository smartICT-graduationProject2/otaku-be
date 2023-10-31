package com.otaku.otakube.repository.approval;

public interface ApprovalRepositoryCustom {
    boolean existsApproval(final Long eventId, final Long userId);
    boolean validateApprovalPermission(final Long approvalId, final Long userId);
}
