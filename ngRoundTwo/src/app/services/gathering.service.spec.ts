import { TestBed } from '@angular/core/testing';

import { GatheringService } from './gathering.service';

describe('GatheringService', () => {
  let service: GatheringService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GatheringService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
