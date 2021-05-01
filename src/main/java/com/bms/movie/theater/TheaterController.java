package com.bms.movie.theater;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bms.movie.util.RegionSupplier;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "theater", description = "theater API")
public class TheaterController {

	private final TheaterService theaterService;
	private final MessageSource messageSource;

	@Operation(summary = "Get all theaters", description = "get theaters based on region key set in cookie", tags = {
			"theater" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TheaterDto.class)))) })
	@GetMapping(value = "/theaters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TheaterDto>> getTheaters() {
		
		String region = RegionSupplier.getRegion.get();
		System.out.println(messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale()));
		return new ResponseEntity<>(theaterService.getTheaters(region), HttpStatus.OK);
	}

	@Operation(summary = "Search theater by name", description = "search theater based on region key set in cookie", tags = {
			"theater" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TheaterDto.class)))) })
	@GetMapping("/theater")
	public ResponseEntity<List<TheaterDto>> searchTheater(
			@Parameter(description = "name of the theater to be searched. Cannot be empty.", required = true) @RequestParam(required = true) String name) {
		String region = RegionSupplier.getRegion.get();
		return new ResponseEntity<>(theaterService.searchTheaters(region, name), HttpStatus.OK);
	}

	@Operation(summary = "add new theater", description = "", tags = { "theater" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TheaterDto.class)))) })
	@PostMapping(value = "/theater", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TheaterDto>> addTheater(
			@Parameter(description = "theater to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = TheaterDto.class)) @RequestBody(required = true) TheaterDto theaterDto) {
		String region = RegionSupplier.getRegion.get();
		return new ResponseEntity<>(theaterService.searchTheaters(region, ""), HttpStatus.OK);
	}

}
