package com.bms.movie.theater;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Schema(description = "Name of the theater.", example = "PVR", required = true, accessMode = AccessMode.READ_ONLY)
	private String name;
	@Schema(description = "rgion of the theater.", example = "panchkula", required = true, accessMode = AccessMode.WRITE_ONLY)
	private String city;

}
