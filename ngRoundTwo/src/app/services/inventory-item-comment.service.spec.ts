import { TestBed } from '@angular/core/testing';

import { InventoryItemCommentService } from './inventory-item-comment.service';

describe('InventoryItemCommentService', () => {
  let service: InventoryItemCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InventoryItemCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
