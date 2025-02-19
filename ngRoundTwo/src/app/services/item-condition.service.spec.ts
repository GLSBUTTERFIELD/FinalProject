import { TestBed } from '@angular/core/testing';

import { ItemConditionService } from './item-condition.service';

describe('ItemConditionService', () => {
  let service: ItemConditionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItemConditionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
