import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class DMXService {
  constructor(private http: HttpClient) {}

  public updateDmxValue(channel: number, value: number): Promise<any> {
    return this.http
      .post(`/api/universe/0/dmx/channel/${channel}/${value}`, null)
      .toPromise();
  }
}
