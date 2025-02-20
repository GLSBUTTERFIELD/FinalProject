import { TestBed } from '@angular/core/testing';

import { GameResourceService } from './game-resource.service';

describe('GameResourceService', () => {
  let service: GameResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameResourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
